import re
from pathlib import Path

HEADING_RE = re.compile(r"^(#{1,3})\s+(.+)$")

def chunk_markdown(path):
    path = Path(path)

    lines = path.read_text(encoding="utf-8").splitlines()

    chunks = []
    current_lines = []
    heading_stack = []

    in_code_block = False
    fence = None

    def flush():
        nonlocal current_lines

        text = "\n".join(current_lines).strip()

        if not text:
            current_lines = []
            return

        section = " > ".join(heading_stack)

        chunks.append({
            "source": path.name,
            "section": section or path.stem,
            "text": text,
            })

        current_lines = []

    for line in lines:
        stripped = line.strip()

        if stripped.startswith("```") or stripped.startswith("~~~"):

            marker = stripped[:3]

            if not in_code_block:
                in_code_block = True
                fence = marker

            elif marker == fence:
                in_code_block = False
                fence = None

            current_lines.append(line)
            continue

        if not in_code_block:
            match = HEADING_RE.match(line)

            if match:
                flush()

                level = len(match.group(1))
                title = match.group(2).strip()

                heading_stack[:] = heading_stack[:level - 1]

                heading_stack.append(title)

                current_lines.append(line)

                continue

            current_lines.append(line)

    flush()

    return chunks
