import json
from urllib.request import Request, urlopen

OLLAMA_URL = "http://localhost:11434"

EMBED_MODEL = "embeddinggemma"
LLM_MODEL = "qwen3:8b"

def _post(path, payload):
    data = json.dumps(payload).encode("utf-8")

    request = Request(
            OLLAMA_URL + path,
            data=data,
            headers={"Content-Type": "application/json"},
            method="POST",
            )

    with urlopen(request, timeout=300) as response:
        return json.loads(response.read().decode("utf-8"))

def embed(texts, model=EMBED_MODEL):
    if isinstance(texts, str):
        texts = [texts]

    result = _post(
            "/api/embed",
            {   "model": model,
                "input": texts,
            },
            )

    return result["embeddings"]

def chat(messages, model=LLM_MODEL):
    result = _post(
            "/api/chat",
            {
                "model": model,
                "messages": messages,
                "stream": False,
                "think": False,
                },
            )
    
    return result["message"]["content"]
