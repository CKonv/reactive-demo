import requests

def gen():
  for i in range(100000):
    yield '{ "name": "test' + str(i) + '" }'

headers = {'Content-type': 'application/x-ndjson'}

r = requests.post("http://localhost:8080/post_flux_get_mono", headers=headers, data=gen())
print(r.content)
