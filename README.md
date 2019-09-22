# MeliExam

Examen de Mercadolibre, en el cual tengo que desarrollar una app que utiliza algunas de las apis para la busqueda de productos

La api fue obtenida de: https://developers.mercadolibre.com.ar/en_us/items-and-searches

# Objetivos:

- Search: buscador de productos y listado de respuestas.
- Product Page: detalle de un producto (al cual se debería poder acceder al tappear uno de los items en el resultado del search).


# Librerias utilizadas:

- https://github.com/Rajagopalr3/CustomEditText -> Para poder personalizar los EditText a gusto
- https://github.com/bumptech/glide -> Permite la carga de imagenes en URL, con sistema de cache
- https://github.com/square/retrofit -> Utilizado para conectarnos a la API
- https://developer.android.com/guide/topics/ui/layout/recyclerview -> Para un listado más óptimo


# TODO:

- Paginación a la hora buscar productos
- ProgressBar (Loading Circle) cuando se hace la busqueda tanto de los productos como del producto, y la paginación
- Error View cuando no se encuentran productos tanto en el Search como en el Producto en general
