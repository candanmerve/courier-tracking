# 🚚 Kurye Takip Sistemi

Bu proje, bir kuryenin konum bilgilerinin takip edilmesini sağlayan basit bir Spring Boot uygulamasıdır. Kuryelerin konumları loglanarak, toplam kat ettikleri mesafe hesaplanabilmekte ve performans takibi yapılabilmektedir.

## 🛠 Kullanılan Teknolojiler

- **Java 17**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **H2 In-Memory Database**
- **Caffeine Cache**
- **Lombok**
- **Postman**

---

## 📦 Proje Özeti

- Mağazaların bilgileri önceden veri tabanına eklenmiştir.
- Uygulama çalıştığında, ilk olarak mağaza verileri H2 veritabanına insert edilir.
- Kuryelerin konumları düzenli olarak loglanır.
- Kuryenin toplam kat ettiği mesafe hesaplanabilir.
- Performans artırımı için mağaza ve kurye bilgileri **Caffeine Cache** ile önbelleğe alınır.
- Yeni bir mağaza eklendiğinde cache otomatik olarak temizlenir.

---

## 🧠 Cache Kullanımı (Caffeine)

- `Courier` ve `Store` objeleri için Caffeine Cache kullanılmaktadır.
- `expireAfterWrite` özelliği ile, veriler cache'e yazıldıktan sonra belirli bir süre içinde erişilse bile süresi dolunca silinir.
- Böylece cache'te güncel olmayan veriler belli bir süre sonra otomatik olarak temizlenmiş olur.
- Yeni bir mağaza eklendiğinde cache temizlenir ve veritabanından güncel veriler tekrar alınır.
---

## 🗄 Veritabanı

- H2 veritabanı, uygulama içinde **in-memory (bellek içi)** çalışmaktadır.
- Uygulama ayağa kalkarken örnek veriler insert edilir.
- H2 Console arayüzü üzerinden veri görüntülenebilir.

> H2 Console:
> ```
> http://localhost:8080/h2-console
> ```
> JDBC URL:
> ```
> jdbc:h2:mem:testdb
> ```
> Kullanıcı adı: `sa`  
> Şifre: (boş)

---

## 🚀 Uygulama Nasıl Başlatılır?

-Projeyi klonlanır veya iletilen ekteki klasör lokal ortama çekilir.

> git clone https://github.com/candanmerve/courier-tracking

-Projeyi çalıştırın:
> mvn clean install

> mvn spring-boot:run

Tarayıcıdan veya Postman üzerinden API'leri test edilebilir.

Örnek API istekleri ekte CourierTrackingApplication.postman_collection.json dosyasında listelenmiştir.
