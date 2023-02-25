# CreditApplication

Proje Konusu:
Bir kredi başvuru sistemi için, kredi başvuru isteklerini alıp ilgili kriterlere göre müşteriye kredi sonucunu dönen servisin içinde yer alacak restful bir uygulamanın Spring Boot framework kullanılarak yazılması ve isteğe bağlı olarak önyüzünün yazılması
Backend 
Kimlik numarası, ad-soyad, aylık gelir, telefon bilgileri, doğum tarihi ve teminat(opsiyonel) kimlik numarasıyla daha önceden yazıldığı varsayılan kredi skoru servisine gidilir ve ilgili kişiye ait kredi skoru alınarak aşağıdaki kurallara göre kullanıcıya kredi sonucu gösterilir. (Onay veya Red olarak iki seçenek olabilir.) 
Frontend (opsiyonel): 
Kimlik numarası, ad-soyad, aylık gelir, telefon bilgileri, doğum tarihi ve teminat(opsiyonel) bilgileri form ile alınır ve kullanıcıya kredi sonucu ve kredi limiti gösterilir. 



#Kurallar:

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
