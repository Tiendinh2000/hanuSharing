# Sử dụng image adoptopenjdk với JDK 8 trên hệ điều hành alpine
FROM adoptopenjdk/openjdk8:alpine

# Cài đặt các biến môi trường
ENV APP_HOME /app
ENV PORT 8081

# Tạo thư mục làm việc
WORKDIR $APP_HOME

# Sao chép các file cần thiết và thực hiện gói ứng dụng
COPY . .

# Chạy Maven để biên dịch ứng dụng
RUN ./mvnw package -DskipTests

# Đặt lệnh khởi động cho ứng dụng
CMD ["java", "-jar", "target/sharing.jar"]
