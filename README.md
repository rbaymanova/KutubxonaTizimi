# Kutubxona Tizimi

Java Spring Boot asosida yozilgan oddiy kutubxona tizimi. Kitoblar, foydalanuvchilar, rezervatsiyalar va reytinglarni boshqarish imkonini beradi.

---

## Texnologiyalar

- Java 21
- Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL
- Lombok
- Postman (API testlari uchun)

---

## Loyihani ishga tushurish

1. Loyihani klon qiling:
   ```bash
   git clone https://github.com/username/kutubxona-tizimi.git
2. docker-compose.yml faylni ishga tushiring
3. Loyihani Intellij Idea orqali ishga tushiring

---

## API Endpointlari
### Foydalanuvchilar (UserController)
| Method   | URL                                    | Tavsif                          |
| -------- | -------------------------------------- | ------------------------------- |
| `GET`    | `/api/users?adminId=1`                 | Barcha foydalanuvchilar         |
| `GET`    | `/api/users/{userId}?adminId=1`        | ID orqali foydalanuvchini olish |
| `POST`   | `/api/users/add?adminId=1`             | Yangi foydalanuvchi qo‘shish    |
| `PUT`    | `/api/users/update/{userId}?adminId=1` | Foydalanuvchini yangilash       |
| `DELETE` | `/api/users/delete/{userId}?adminId=1` | Foydalanuvchini o‘chirish       |

###  Kitoblar (BookController)
| Method   | URL                                           | Tavsif                    |
| -------- | --------------------------------------------- | ------------------------- |
| `GET`    | `/books`                                      | Barcha kitoblar           |
| `GET`    | `/books/{bookId}`                             | Kitobni ID orqali olish   |
| `POST`   | `/books/add?adminId=1`                        | Yangi kitob qo‘shish      |
| `PUT`    | `/books/update/{bookId}?adminId=1`            | Kitobni to‘liq yangilash  |
| `PATCH`  | `/books/update/{bookId}?adminId=1&name=...`   | Kitobni qisman yangilash  |
| `DELETE` | `/books/delete/{bookId}?adminId=1`            | Kitobni o‘chirish         |
| `POST`   | `/books/rating/add/{bookId}?userId=3&score=5` | Kitobga baho qo‘yish      |
| `GET`    | `/books/rating/{bookId}`                      | Kitobning o‘rtacha bahosi |

### Kitob band qilish, topshirish (ReserveController)
| Method | URL                                                                  | Tavsif                                     |
| ------ | -------------------------------------------------------------------- | ------------------------------------------ |
| `POST` | `/reservations/reserve?adminId=1&bookId=5&userId=3`                  | Kitobni rezervatsiya qilish                |
| `POST` | `/reservations/borrow?adminId=1&reserveId=2`                         | Rezervatsiyani "borrowed" deb belgilash    |
| `POST` | `/reservations/accept?adminId=1&reserveId=2`                         | Kitobni qaytarilgan deb belgilash          |
| `POST` | `/reservations/remove-expired?adminId=1`                             | Muddati o‘tgan rezervatsiyalarni o‘chirish |
| `GET`  | `/reservations/penalty?adminId=1&reservationId=2&dueDate=2024-05-01` | Jarimani hisoblash                         |

---

## Ekran Rasmlari
Rasmlar /pics papkada joylashgan. U yerda Postgres table'larini va Postman testlarni ko'rishingiz mumkin

 ---

## Muallif
Ruxshona Baymanova
GitHub: @rbaymanova

---

