# Dự Án Kiểm Thử Tự Động (AutoTest)

## I. Mô Tả
Dự án kiểm thử tự động này được xây dựng nhằm mục đích kiểm tra các tính nănng đặt hàng. 
Sử dụng các công cụ như **TestNG**, **Selenium**, **ApachePoi**, **Log4j**, **ExtendReport** và **Allure** để thực hiện các bài kiểm thử tự động và tạo báo cáo kết quả.
Mục tiêu của dự án là phát hiện lỗi sớm và đảm bảo chất lượng của phần mềm thông qua các bài kiểm thử tự động.

## Các công cụ sử dụng
- **Selenium WebDriver**: Dùng để kiểm thử UI trên các trình duyệt.
- **TestNG**: Công cụ kiểm thử để chạy các bài kiểm thử tự động và báo cáo kết quả.
- **ApachePoi**: đọc file data dạng excel
- **Log4j**: ghi log trong quá trình run test case
- **Allure**: Công cụ để tạo và trình bày báo cáo kết quả kiểm thử theo cách trực quan.

## II. Cài Đặt

### 1. Clone Repository:
- Clone repository này về máy của bạn bằng lệnh sau:

`git clone https://github.com/DungPham6Sotatek/Assignmnent2`

### 2. Cài đặt phụ thuộc và cấu hình môi trường: 
- Trỏ vào thư mục đã clone từ github về (ví dụ: C:\Users\admin\Assignmnent2)

`mvn install`

### 3. Cài đặt môi trường:
 - Cài đặt Java

 - Cài đặt Maven

## IV. Chạy kiểm thử:
`mvn test -Dtest=Section2_MagnetoTestStore`

(hiện nay URL của assignment này đang lỗi, nên có thể chạy thử bên dưới để xem kết quả)

`mvn test -Dtest=TestAlada`


## V. Tạo báo cáo:
`allure serve allure-results`

## VI. Những điểm cần chú ý:
- **WebDriverManager**: Không cần phải tải WebDriver thủ công, vì **WebDriverManager** tự động quản lý và tải WebDriver cho bạn.
- **Cài Đặt Phụ Thuộc**: Các thư viện cần thiết (Selenium, TestNG, Allure, WebDriverManager, Apache POI, Log4j , Extent Report) đã được khai báo trong `pom.xml`. Bạn chỉ cần chạy `mvn install` để tải về các phụ thuộc này.
- **Chạy Kiểm Thử**: Các lệnh Maven cho phép chạy kiểm thử UI, và trên các môi trường khác nhau.
- **Báo Cáo Kết Quả**: Hướng dẫn cách tạo và xem báo cáo Allure sau khi chạy kiểm thử.

## Kết luận:
- Với sự cài đặt qua **Maven**, và việc sử dụng **WebDriverManager**, bạn không cần phải thêm cấu hình thủ công cho WebDriver trong môi trường của mình.
- README này đã mô tả tất cả các bước cần thiết để người khác có thể sử dụng và đóng góp vào dự án của bạn.
