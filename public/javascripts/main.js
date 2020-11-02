let txt ="";
let finaltext = "";
let count = 1;
function search() {
var searchkey = document.getElementById("searchTerm").value;
jQuery.ajax({
            url: "http://localhost:9000/search/" + searchkey,
            type: "GET",

            contentType: 'application/json; charset=utf-8',
            success: function(resultData) {
            	if(count <= 10) {
            	count++;
            	      }
     			 else {
      			finaltext = "";
      			txt = "";
      			count = 1;
      			}
      			debugger;
                var result = JSON.stringify(resultData);
				var tweets = resultData.data.tweets;
				 myObj = JSON.parse(JSON.stringify(tweets));
      			 txt += "<table border='1'>"
      			 txt += "<caption>"+ "Search terms:" + "<a id=\"" + searchkey + "\"onclick=\"wordStats(" + searchkey + ")\"" +  ">" + searchkey + "</a>  " +
      			   resultData.data.sentiment +
      			  "</caption>"
 				 for (x in myObj) {
				   	 txt += "<tr><td>" 
				   	 + x + "." + "</td>" +
				   	  "<td id=\"" + myObj[x].userScreenName + "\"onclick=\"displayUser(" + myObj[x].userScreenName + ")\"" +  ">" + myObj[x].userScreenName + "</td>" +
				   	  "<td>" + myObj[x].tweetText + "</td></tr>";
				  }
     			 txt += "</table>"    
     			 finaltext = txt + finaltext;
     			 txt = "";
			     document.getElementById("demo").innerHTML = finaltext;
            },
            error : function(jqXHR, textStatus, errorThrown) {
            },

            timeout: 120000,
        })
}

function displayUser(username) {
if(username.length >1) {
alert(username[0].textContent);
}
else {
alert(username.textContent);
}
}
function wordStats(keyterm) {
	alert(keyterm.textContent);
}
