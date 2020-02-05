def call(jsondata){
def jsonString = jsondata
//println(jsonString)
def jsonObj = readJSON text: jsonString
println(jsonObj.alm)

String a=jsonObj.alm.projects.project.project_name
String projectName=a.replaceAll("\\[", "").replaceAll("\\]","");
  
env.name = projectName
  
def length = 3
def projLength = name.size()
 println(projLength)
 if(projLength>=3){
  key=name.substring(0, Math.min(projLength, length)).toUpperCase();
 }
 else {
   def appendStr = "";
   int currentLength=length;
   int len = currentLength%projLength;
   int repeat = currentLength/projLength;
   for (int i=0;i<repeat;i++) {
    appendStr = appendStr + name;
   }
   appendStr=appendStr+name.substring(0, Math.min(projectName.length(), len));
   key=appendStr.toUpperCase();
   }
 def projKey = key
 
 println(projKey)
  
  
  
  
 httpRequest authentication: 'jira_password',
 customHeaders: [[maskValue: false, name: 'Content-Type', value: 'application/json'],
                 [maskValue: false, name: 'Accept', value: 'application/json']], 
 httpMode: 'DELETE',  url: "http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/project/${projKey}"
}
    
/*curl -X DELETE \
  http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/project/EDN \
  -H 'accept: application/json' \
  -H 'authorization: Basic cmlnOmRpZ2l0YWxyaWdAMTIz' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 106b5efa-4bcf-e924-b895-abda8ffe17a7'
  
*/
