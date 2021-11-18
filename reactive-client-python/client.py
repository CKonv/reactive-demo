import requests
import json

data = [
  { "name": "name1" },
  { "name": "name2" },
  { "name": "name3" }
]

data_to_post = '\n'.join(json.dumps(d) for d in data)
headers = {'Content-type': 'application/x-ndjson'}
r = requests.post("http://localhost:8080/post_flux_get_mono", headers=headers, data=data_to_post)
print(r.content)
