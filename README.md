# CreditApplication

## Proje Konusu:
* Bir kredi başvuru sistemi için, kredi başvuru isteklerini alıp ilgili kriterlere göre müşteriye kredi sonucunu dönen servisin içinde yer alacak restful bir uygulamanın Spring Boot framework kullanılarak yazılması ve isteğe bağlı olarak önyüzünün yazılması
* !! Ekran Resimleri https://github.com/E2Kerem01/CreditApplication/tree/master/images
### Backend 
* Kimlik numarası, ad-soyad, aylık gelir, telefon bilgileri, doğum tarihi ve teminat(opsiyonel) kimlik numarasıyla daha önceden yazıldığı varsayılan kredi skoru servisine gidilir ve ilgili kişiye ait kredi skoru alınarak aşağıdaki kurallara göre kullanıcıya kredi sonucu gösterilir.
### Frontend (opsiyonel): 
* Kimlik numarası, ad-soyad, aylık gelir, telefon bilgileri, doğum tarihi ve teminat(opsiyonel) bilgileri form ile alınır ve kullanıcıya kredi sonucu ve kredi limiti gösterilir. 



# Kurallar:

* Sisteme yeni kullanıcılar tanımlanabilir, mevcut müşteriler güncellenebilir veya silinebilir.
* Kredi skoru 500’ün altında ise kullanıcı reddedilir. (Kredi sonucu: Red)
* Kredi skoru 500 puan ile 1000 puan arasında ise ve aylık geliri 5000 TL’nin altında ise
* Kullanıcının kredi başvurusu onaylanır ve kullanıcıya 10.000 TL limit atanır. (Kredi Sonucu: Onay). Eğer teminat vermişse teminat bedelinin yüzde 10 u kadar tutar kredi limitine eklenir.
* Kredi skoru 500 puan ile 1000 puan arasında ise ve aylık geliri 5000 TL ile 10.000TL arasında ise kullanıcının kredi başvurusu onaylanır ve kullanıcıya 20.000 TL limit atanır. (Kredi Sonucu:Onay) Eğer teminat vermişse teminat bedelinin yüzde 20 si kadar tutar kredi limitine eklenir.
* Kredi skoru 500 puan ile 1000 puan arasında ise ve aylık geliri 10.000 TL’nin üstünde ise kullanıcının kredi başvurusu onaylanır ve kullanıcıya AYLIK GELİR BİLGİSİ * KREDİ LİMİT ÇARPANI/2 kadar limit atanır. (Kredi Sonucu:Onay) Eğer teminat vermişse teminat bedelinin yüzde 25 i kadar tutar kredi limitine eklenir.
* Kredi skoru 1000 puana eşit veya üzerinde ise kullanıcıya AYLIK GELİR BİLGİSİ * KREDİ LİMİT ÇARPANI kadar limit atanır. (Kredi Sonucu: Onay) Eğer teminat vermişse teminat bedelinin yüzde 50 si kadar tutar kredi limitine eklenir.
* Kredinin neticelenmesi sonucunda ilgili başvuru veritabanına kaydedilir. Daha sonrasında ise ilgili telefon numarasına bilgilendirme SMS’i gönderilir ve endpoint’ten onay durum bilgisi (red veya onay), limit bilgisi dönülür.
* Gerçekleştirilmiş bir kredi başvurusu sadece kimlik numarası ve doğum tarihi bilgisi ile sorgulanabilir. Doğum tarihi ve kimlik bilgisi eşleşmezse sorgulanamamalıdır. 
Notlar: Kredi limit çarpanı varsayılan olarak 4’tür.


# Technologies Backend

* Java 1.8
* Spring Framework
* PostgreSql Database

# Technologies Frontend

* JavaScript
* React.js

# Spring Features

* Spring Data JPA
* Spring Boot Maven Plugin
* Spring Security Core
* PostgreSql Driver
* Lombok 
* Springdoc Openapi

# Credit Applications Screenshots

## User Sign and Login Pages

![Ekran Alıntısı 1](https://user-images.githubusercontent.com/62817020/221602401-b8841edf-90ab-49b2-ba0e-c1b417e90490.png)

## Credit Application Page and response

![Ekran Alıntısı 2](https://user-images.githubusercontent.com/62817020/221602514-a1757b30-e0d2-4b01-92d2-2d41e0f389ec.png)
![Ekran Alıntısı 3](https://user-images.githubusercontent.com/62817020/221602635-732a841f-a3ab-43e6-b0ec-2700adcb56d6.png)

## Credit Sms

![WhatsApp Görsel 2023-02-26 saat 19 28 16](https://user-images.githubusercontent.com/62817020/221602716-075d3077-1f71-4832-898a-bf2db7f1f726.jpg)

## Postman and Swagger Documentation

![postman](https://user-images.githubusercontent.com/62817020/221602872-c0f0263c-31cd-4c29-96ff-262933791985.png)
![swagger](https://user-images.githubusercontent.com/62817020/221602886-ec0bc59b-fef1-48cf-8268-a8f828a8c63f.png)


