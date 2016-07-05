# Coding dojo

Compartimos con ustedes el código utilizado en la coding dojo realizada por Globallogic.

La aplicación se trata de un lector de RSS (en xml) donde se comparten las novedades de Globallogic. Contiene un listado de items y un detalle con mas información de los mismos.

La api se encuentra mockeada en [Apiary](https://apiary.io/).

## Arquitectura clean

![Clean architecture](https://s32.postimg.org/44nr3ojbp/Architecture.png)

## Tests

El ejemplo cuenta con 3 test principales:
* Mapper: Para asegurar el funcionamiento del pasaje de Dto a Model
* UseCase: Para asegurar el funcionamiento de la lógica de negocio tanto para el caso de éxito como el caso de error
* Presenter: Para asegurar el comportamiento visual esperado de la aplicación así como de la interacción con el caso de uso para realizar búsquedas.

En todos los casos el repositorio se encuentra mockeado ya que no nos interesa testear casos de conexión ni la librería para hacer los request.

[![Config](https://s31.postimg.org/sp213dx47/Android_test_config.jpg)](https://s31.postimg.org/osop7ec4r/Android_test_config.jpg)
[![Coverage](https://s31.postimg.org/7qvv5ax9j/Android_test_config_coverage.jpg)](https://s31.postimg.org/z1h6d806j/Android_test_config_coverage.jpg)
