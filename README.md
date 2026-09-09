# Simple RAG

* Make chunks of the source (markdown slides)
* Embedding the chunks with `EMBED_MODEL`
    * Normalized
* Saving vectors and chunks in `data/`
* When searching
    * embed the query to get the vector
    * calculate the cosine similarity with the data vectors
    * Feed top results to the LLM

```
python chunker.py # build index
```

```
pythond rag.py <query>
```

## TODO

* Evaluating the system
    * Using past Piazza QA, manual question answers
    * Let a smarter LLM evaluate the answers
* Simple query at a time, no context
* Adding more materials
* OpenRouter somewhere
* Maybe train an LLM just for the course
