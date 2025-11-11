# cosmic-sentry-api

![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Micronaut](https://img.shields.io/badge/Micronaut-376B9B?style=for-the-badge&logo=microeditor&logoColor=white)

A Kotlin-based REST backend application that aggregates NASA data for near earth objects. 
Using Micronaut as the service framework.

## Setting up Redis

### Using Docker

If you have Docker Compose already installed, then it's as simple as running the following command:

```shell
docker-compose up -d
```

### Using Podman

If you have Podman installed, first you'll need to install Podman Compose. You can do so by running the following command:

```shell
brew install podman-compose
``` 

Then it's as simple as running the following command:

```shell
podman-compose up -d
```

## References

[Micronaut Documentation](MICRONAUT.md)
