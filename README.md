# AI Note Taker

I wanted to try building my first ai agent, and came up with this simple idea. The agent takes  text as input att outputs a summarized version of the text i Markdown format. Since I'm running the ai locally on my old laptop which I've been using as a home server, I chose the lightweight llama3.2:1b model. So the resources are limited but it's been a fun experiment so far.

The project implements a decoupled pipeline to separate input, processing, and storage:

- REST API: Receives raw text via a POST request and publishes a task to the note-requests exchange.

- RabbitMQ: Acts as the message broker and buffer, ensuring reliability even under heavy load.

- Note Processor: Consumes requests, invokes the local Ollama instance, and routes the generated summary to the summaries exchange.

- File Writer: Listens for completed summaries and persists them to disk with unique, sortable timestamps.

## Tech Stack

- Framework: Quarkus (Java)

- AI Orchestration: LangChain4j

- Model: Llama 3.2 (running via Ollama)

- Messaging: RabbitMQ (SmallRye Reactive Messaging)

- Containerization: Docker (managed via Quarkus Dev Services)
