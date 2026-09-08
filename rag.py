import sys

from search import search
from ollama_client import chat

def answer(question):
    results = search(question, k=5)

    context_parts = []

    for i, result in enumerate(results, start=1):
        context_parts.append(
                            f"""
[{i}]
Source: {result["source"]}
Section: {result["section"]}

{result["text"]}
""".strip()
        )

        context = "\n\n".join(context_parts)

        messages = [
                {
                    "role": "system",
                    "content": """
You are a tutor for a university programming course.

Use the supplied course material as the primary source for
answering the student's question.

Explain concepts clearly rather than merely giving an answer.

When you use course material, cite it using [1], [2], etc.

If the supplied material does not contain enough information
to answer the question, say so rather than inventing course
content.
""".strip()
        },
        {
            "role": "user",
            "content": f"""
COURSE MATERIAL

{context}

STUDENT QUESTION

{question}
""".strip()
        }
        ]

    return chat(messages)

def main():

    if len(sys.argv) < 2:
        print('Usage: python rag.py "your question"')
        return

    question = " ".join(sys.argv[1:])

    response = answer(question)

    print()
    print(response)


if __name__ == "__main__":
    main()
