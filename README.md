1) Плагин необходимо скомпилировать (mvn clean install), чтобы он попал в локальный maven репозторий.
2) В проекте указать подключение к плагину. 
2.1) В теге configuration указать возможные параметры: 
В repositoryDirectory указывается путь к корневой директории для репозиториев. Является обязательным. Пример: src/main/java/ru/company/customer/project/repository
В entityDirectory указывается путь к корневой директории для сущностей. Является обязательным. Примерsrc/main/java/ru/company/customer/lkau/entity
В repositoryFilePattern указывается шаблон поиска файлов интерфесов репозиториев. Не обязательное. По умолчанию ".*Repository.java".
В entityFilePatternуказывается шаблон поиска файлов интерфесов репозиториев. Не обязательное. По умолчанию ".*Entity.java".
2.2) В тегах executions и execution указать id = process, phase = validate 
2.3) В теге goals указать goal = analyzer
       
3) Плагин будет запущен при install, validate и цели entity-graph-analyzer:analyzer
