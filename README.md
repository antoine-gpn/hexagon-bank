### Le projet est Dockerisé et contient 4 services :
``API (localhost:8080)``<br/>
``Front (localhost:3000)``<br/>
``PHPMyAdmin (localhost:8081 | logs : root/root)``<br/>
``BDD (mysql)``


### Pour lancer le projet, exécutez la commande :<br/>
``docker-compose up --build``

### Documentation API :
``http://localhost:8080/swagger-ui/index.html``

``Collection Postman à la racine : postman_collection.json``

### Découpage du projet hexagonal en 4 parties :


``- Core/domain (Ports in/out avec interfaces / Entité Transaction)``<br/>
``Persistence (Implémentation du Repository / Echanges avec la BDD MySql)``<br/>
``- API Spring (Controller renvoyant les endpoints HTTP)``<br/>
``- Front ReactJS (Interface interagissant avec l'API)``


### Capture d'écran du rendu Front :
<br/>
