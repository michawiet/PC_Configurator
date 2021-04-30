# PC_Configurator

Sprytny poradnik połączenia bazy danych H2 ze springiem.

1.Pobieramy bazę H2 z tego linku https://h2database.com/h2-setup-2019-03-13.exe

2.Instalujemy ją na komputerze gdzie chcemy

3.Uruchamiamy wiersz poleceń jako administrator

4.W wierszu poleceń przechodzimy do katalogu bin zainstalowanej bazy H2

5.Wpisujemy komendę: java -cp h2-*.jar org.h2.tools.Shell  (* - wersja pliku jar np. h2-1.4.199.jar)

6.W URL podajemy nazwę naszej bazy: jdbc:h2:./nazwa_db

7.W nazwie użytkownika podajemy nazwę naszego użytkownika

8.I hasło

9.Oczywiście musimy potwierdzić hasło

10.W projekcie w pliku application.properties wklejamy: 

spring.datasource.url=jdbc:h2:*.mv.db  #(* - ścieżka do pliku)

spring.datasource.driverClassName=org.h2.Driver

spring.datasource.username=pcc

spring.datasource.password=pcc

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

spring.h2.console.enabled=true
