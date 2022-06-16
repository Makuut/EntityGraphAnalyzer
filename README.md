  Плагин определяет наличие полей, указанных в @EntityGraph в методах репозиториях и в @NamedEntityGraph(s) сущностей у классов-сущностей (имеют @Entity). При отсутствии полей из графа в сущности выводится сообщение.
  
  Врианты подключения: 
  1) Плагин необходимо скомпилировать (mvn clean install), чтобы он попал в локальный maven репозторий. В проекте указать подключение к плагину. 
В теге configuration указать возможные параметры: в sourceRoot указывается путь к корневой папке. Если не указывать явно, будет src/main/java по умолчанию. В тегах executions и execution указать id = process, phase = validate. В теге goals указать goal = analyze. Плагин будет запущен при install, validate и цели entity-graph-analyzer:analyzer.
  2) В тестовом классе EntityGraphAnalyzerPluginTest в переменной sourceRoot указать путь к проекту. Запустить тест execute.

  Использование плагина: 
  Для обоих типов графов выводимое сообщение имеет вид "В классе Building отсутствует поле-сущность windows, указанное в streets.buildings.windows". Для нахождения неверного графа необходимо использовать поиск по "windows" который приведет либо к графу в репозитории, либо в сущности или поиск по "streets.buildings.windows" который приведет к графу в репозитории. 
  
  P. S. Сущности и репозитории дожлны находиться внутри указанного sourceRoot.
