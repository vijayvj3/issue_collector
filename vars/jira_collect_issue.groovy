//import groovy.json.JsonSlurper 
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NonCPS
collectissues(String data){
def jsonSlurper = new JsonSlurper() 
def resultJson = jsonSlurper.parseText(data)
def projectName = resultJson.key
echo "$projectName"
 httpRequest authentication: 'jira_password',
  customHeaders: [[maskValue: false, name: 'Accept', value: 'application/json']], 
     httpMode: 'GET', url:"http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/search?jql=project%3D${projectName}&fields=key%2Csummary%2Cdescription&maxResults=1000&startAt=0"

 }
}

*/
def call(jsondata){
def jsonString = jsondata
//println(jsonString)
def jsonObj = readJSON text: jsonString
println(jsonObj.alm)

String a=jsonObj.alm.projects.project.project_name
String projectName=a.replaceAll("\\[", "").replaceAll("\\]","");
  
env.name = projectName

/* sh """curl -X POST \
  http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/search \
  -H 'accept: application/json' \
  -H 'authorization: Basic cmlnOmRpZ2l0YWxyaWdAMTIz' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 03a3b589-c1d3-4234-8043-9335bf162947' \
  -d '{"jql":"project = EDN","startAt":0,"maxResults":2,"fields":["id","key"]}'
 """
 */
sh """curl -X GET \
 'http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/search?jql=project%3D"${projectName}"%20order%20by%20duedate&fields=id%2Ckey' \
  -H 'accept: application/json' \
  -H 'authorization: Basic cmlnOmRpZ2l0YWxyaWdAMTIz' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  node <<< "var o = $(cat); console.log(JSON.stringify(o, null, 4));
 
"""
}
/*def call(){
 def request = libraryResource 'data1.json'
 collectissues(request)
}
*/
