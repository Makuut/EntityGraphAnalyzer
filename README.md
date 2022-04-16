1) Плагин необходимо скомпилировать локально (mvn clean install), чтобы он попал в локальный maven репозторий.
2) В проекте указать подключение к плагину внутри тегов plugins и plugin
                groupId = ru.makuut, artifactId = entity-graph-analyzer, version = 1.0-SNAPSHOT. 
                В теге configuration -> 
                    repositoryDirectory = src/main/java/ru/company/customer/project/repository,
                    repositoryFilePattern = .*Repository.java,
                    entityDirectory = src/main/java/ru/company/customer/lkau/entity,
                    entityFilePattern = .*Entity.java.
                В тегах executions и execution ->
                        id = process,
                        phase = validate.
                        В теге goals ->
                            goal = analyze eg
       
3) В проекте запустить validate в выпадающем списке Lifecycle или entity-graph-analyzer:analyze eg в выпадающем списке Pugins и entity-graph-analyzer
            
