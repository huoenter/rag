import json
from pathlib import Path

import numpy as np

from chunker import chunk_markdown
from ollama_client import embed, EMBED_MODEL

ROOT = Path(__file__).parent
SLIDES_DIR = ROOT / "course" / "slides"
DATA_DIR = ROOT / "data"

def main():
    chunks = []

    for path in sorted(SLIDES_DIR.rglob("*.md")):
        print(f"Reading {path.name}")

        file_chunks = chunk_markdown(path)
        chunks.extend(file_chunks)

    if not chunks:
        raise RuntimeError("No Markdown chunks found.")

    print(f"\nFound {len(chunks)} chunks.")

    texts = [chunk["text"] for chunk in chunks]

    print(f"Embedding with {EMBED_MODEL}...")

    embeddings = np.array(
            embed(texts),
            dtype=np.float32
            )

    # Normalize every vector to length 1

    norms = np.linalg.norm(
            embeddings,
            axis=1,
            keepdims=True
            )

    embeddings = embeddings / np.maximum(norms, 1e-12)

    DATA_DIR.mkdir(exist_ok=True)

    np.save(DATA_DIR / "embeddings.npy", embeddings)

    with open(DATA_DIR / "chunks.json",
              "w",
              encoding="utf-8") as f:
        json.dump(
                chunks,
                f,
                indent=2,
                ensure_ascii=False)

    with open(DATA_DIR / "index.json",
              "w",
              encoding="utf-8") as f:

        json.dump(
            {
                "embedding_model": EMBED_MODEL,
                "num_chunks": len(chunks),
                "dimension": embeddings.shape[1],
            },
            f,
            indent=2,
        )

    print()
    print("Index built.")
    print("Embedding shape:", embeddings.shape)

if __name__ == "__main__":
    main()
