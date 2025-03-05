# Sử dụng JDK 21 từ Eclipse Temurin
FROM eclipse-temurin:21-jdk

# Đặt thư mục làm việc trong container
WORKDIR /app

# Copy file pom.xml và cài đặt dependencies trước

COPY .mvn /app/.mvn
COPY mvnw /app/mvnw

RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

# Copy toàn bộ source code vào container
COPY src ./src

# Build ứng dụng bằng Maven (bỏ qua test)
RUN ./mvnw clean package -DskipTests

# Đổi tên file JAR thành app.jar
RUN mv target/*.jar app.jar

# Mở cổng ứng dụng
EXPOSE 3000

# Chạy ứng dụng
CMD ["java", "-jar", "app.jar"]
# update file name