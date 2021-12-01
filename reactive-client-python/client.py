import requests

def gen():
  for i in range(10):
    yield '{ "o1_id": ' + str(i) + ', "name_keys": ["test_name"] }'

my_list = ['{"o1_id": 1, "name_keys": ["test_name_1","test_name_2"]}',
           '{"o1_id": 2, "name_keys":["test_name_1"]}',
           '{"o1_id": 3, "name_keys":[]}',
           '{"o1_id": 4, "name_keys":["test_name_1","test_name_3"]}',
           '{"o1_id": 5, "name_keys":["test_name_3","test_name_2"]}']

headers = {'Content-type': 'application/x-ndjson'}

r = requests.post("http://localhost:8080/post_flux_get_mono", headers=headers, data=iter(my_list))
# r = requests.get("http://localhost:8080/get_all_as_flux")
print(r.content)
