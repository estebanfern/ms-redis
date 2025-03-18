FROM amazoncorretto:21-alpine-full AS compiler
WORKDIR /build
COPY . .
RUN ./gradlew clean build -x test

FROM amazoncorretto:21-alpine-full
WORKDIR /app
ARG MODULE=ms-clientes
ARG VERSION=0.0.1-SNAPSHOT
ENV TZ=America/Asuncion
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
COPY --from=compiler /build/${MODULE}/build/libs/${MODULE}-${VERSION}.jar /app/app.jar
COPY --from=compiler /build/${MODULE}/src/main/resources/application.yaml /app/config/application.yml
CMD ["java", "-jar", "/app/app.jar", "--spring.config.location=file:///app/config/application.yml"]
