1) Плагин необходимо скомпилировать (mvn clean install), чтобы он попал в локальный maven репозторий.
2) В проекте указать подключение к плагину. 
2.1) В теге configuration указать возможные параметры: 
В sourceRoot указывается путь к корневой папке. Если не указывать явно, будет src/main/java по умолчанию.
2.2) В тегах executions и execution указать id = process, phase = validate 
2.3) В теге goals указать goal = analyze.
3) Плагин будет запущен при install, validate и цели entity-graph-analyzer:analyzer.
