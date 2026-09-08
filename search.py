import json
import sys
from pathlib import Path

import numpy as np

from ollama_client import embed

ROOT = Path(__file__).parent
DATA_DIR = ROOT / "data"

def load_index():
    embeddings = np.load(DATA_DIR / "embeddings.npy")

    with open(
            DATA_DIR / "chunks.json",
            encoding="utf-8"
            ) as f:
        chunks = json.load(f)

    with open(
            DATA_DIR / "index.json",
            encoding="utf-8"
            ) as f:
        metadata = json.load(f)

    return embeddings, chunks, metadata

def search(query, k=5):

    embeddings, chunks, metadata = load_index()

    embedding_model = metadata["embedding_model"]

    query_vector = np.array(
            embed(query, model=embedding_model)[0],
            dtype=np.float32)

    query_vector /= max(
            np.linalg.norm(query_vector),
            1e-12)

    scores = embeddings @ query_vector

    best_indices = np.argsort(scores)[::-1][:k]

    results = []

    for index in best_indices:
        result = dict(chunks[index])
        result["score"] = float(scores[index])

        results.append(result)

    return results

def main():

    if len(sys.argv) < 2:
        print('Usage: python search.py "your question"')
        return

    query = " ".join(sys.argv[1:])

    results = search(query)

    for i, result in enumerate(results, start=1):

        print()
        print("=" * 70)
        print(
            f"{i}. score={result['score']:.4f} "
            f"{result['source']} :: {result['section']}"
        )
        print("-" * 70)

        print(result["text"])


if __name__ == "__main__":
    main()
