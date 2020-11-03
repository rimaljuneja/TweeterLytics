let txt ="";
let finaltext = "";
let count = 1;
Spinner();
Spinner.hide();
function search() {
var searchkey = document.getElementById("searchTerm").value;
Spinner.show();
jQuery.ajax({
            url: "http://localhost:9000/search/" + searchkey,
            type: "GET",

            contentType: 'application/json; charset=utf-8',
            success: function(resultData) {
            	if(count < 11) {
            	count++;
            	}
     			 else {
      			finaltext = "";
      			txt = "";
      			count = 1;
      			}
      			if(count === 11) {
      			document.getElementById('goButton').disabled= true;
      			}
      			var tweetcount = 1;
                var result = JSON.stringify(resultData);
				var tweets = resultData.data.tweets;
				 myObj = JSON.parse(JSON.stringify(tweets));
      			 txt += "<table border='1'>"
      			 txt += "<caption>"+ "Search terms:" + "<a id=\"" + searchkey + "\"onclick=\"wordStats(\'" + searchkey + "\')\"" +  ">  " + searchkey + "</a>  " +
      			   processSentiment(resultData.data.sentiment) +
      			  "</caption>"
 				 for (x in myObj) {
				   	 txt += "<tr><td>" 
				   	 + tweetcount + "." + "</td>" +
				   	  "<td>" +  "<a id=\"" + myObj[x].userScreenName + "\"onclick=\"displayUser(" + myObj[x].userScreenName + ")\"" +  ">" + "@" + myObj[x].userScreenName + "</a></td>" +
				   	  "<td>" + myObj[x].tweetText + "</td></tr>";
				   	  tweetcount++;
				  }
     			 txt += "</table>"    
     			 finaltext = txt + finaltext;
     			 txt = "";
			     document.getElementById("demo").innerHTML = finaltext;
			     Spinner.hide();
            },
            error : function(jqXHR, textStatus, errorThrown) {
            Spinner.hide();
            },

            timeout: 120000,
        })
}
function clearInput() {
document.getElementById('searchTerm').value = '';
}
function processSentiment(sentiment) {
	if(sentiment == "neutral") {
	return ":-|";
	}
	else if(sentiment == "happy") {
	return ":-)";
	}
	else {
	return ":-(";
	}
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
	alert(keyterm);
}
