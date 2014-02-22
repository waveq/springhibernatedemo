#springhibernatedemo
===================

Aplikacja zaliczeniowa na przedmiot Technologie utrwalania danych dla języka Java, prezentująca działanie:
* Spring
* Hibernate
* JUnit

Szkielet aplikacji pochodzi z https://github.com/KubaNeumann/shdemo

Kod napisany przeze mnie znajduje się w klasach:
* src/main/java/com.waveq.przykladspring.domain.Ball.java
* src/main/java/com.waveq.przykladspring.domain.Player.java
* src/main/java/com.waveq.przykladspring.service.Manager.java
* src/main/java/com.waveq.przykladspring.service.ManagerHibernateImplementation.java
* src/test/java/com.waveq.przykladspring.service.ManagerTest.java

Aplikacja obejmuje CRUD do 2 połączonych ze sobą tabel player `one` `to` ball `many`
Do każdej metody jest napisany test sprawdzający jej poprawność.

Skrypt do uruchomienia bazy danych HSQLDB-2-2-4 znajduje się w folderze scripts.
