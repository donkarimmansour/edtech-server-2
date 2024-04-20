import requests
import json

cours = "Résumé développement mobile Révolution Mobile (apparition des smartphones) Évolution des réseaux mobiles : évolution des générations (1G à la 5G) Évolution du hardware : Les progrès dans : les processeurs, les caméras, les écrans et les capteurs. Évolution des utilisateurs : Plus en plus dépendants des smartphones, avertis, exigeants, mobiles. Évolution des systèmes d’exploitation : conçus pour fonctionner efficacement sur des appareils avec des écrans tactiles et des configurations matérielles variées, en tirant parti des fonctionnalités spécifiques aux appareils mobiles. Spécifités Hardware Petite taille de l'écran et du clavier Écran tactile multitouch Vitesse du processeur : Optimiser le code pour des performances efficaces sur différents niveaux de puissance de processeur. Taille de la mémoire Les applications doivent être optimisées pour utiliser efficacement la mémoire disponible, évitant ainsi les surcharges qui pourraient ralentir le fonctionnement. Batterie : Concevoir de circuits adéquats. Langage de programmation dépendant du constructeur : Cela peut nécessiter des ajustements pour assurer la compatibilité avec"
url = "http://localhost:8080/createQuiz"
headers = {'Content-Type': 'application/json'}
data = json.dumps({"cours": cours})

response = requests.post(url, headers=headers, data=data)

# Print the status code and response body
print("Status code:", response.status_code)
print("Response body:", response.json())