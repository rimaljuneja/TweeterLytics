let txt ="";
let finaltext = "";
let count = 1;
Spinner();
Spinner.hide();
var input = document.getElementById("searchTerm");
input.addEventListener("keyup", function(event) {
  if (event.keyCode === 13) {
	search();
	}
});

/*function to show tweet words by search term */
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
				   	  "<td>" +  "<a id=\"" + myObj[x].userScreenName + "\"onclick=\"displayUser(\'" + myObj[x].userScreenName + "\')\"" +  ">" + "@" + myObj[x].userScreenName + "</a></td>" +
				   	  "<td>" + displayHashTags(myObj[x].tweetText) + "</td></tr>";
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

//function to display hashtags
function displayHashTags(input) {
input = input.replace(/(^|\s)(#[a-z\d-]+)/ig, "$1<a onclick=\"processHashTags('$2')\">$2</a>");
return input;
}

//function to process hashtags
function processHashTags(hashtag) {
var regexp = /#(\S)/g;
hashtag = hashtag.replace(regexp, '$1');
Spinner.show();
jQuery.ajax({
            url: "http://localhost:9000/searchbyhashtag/" + (hashtag),
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
      			debugger;
      			var tweetcount = 1;
                var result = JSON.stringify(resultData);
				var tweets = resultData.data.tweets;
				 myObj = JSON.parse(JSON.stringify(tweets));
      			 txt += "<table border='1'>"
      			 txt += "<caption>" + "Hashtag Result:" + hashtag
      			  "</caption>"
 				 for (x in myObj) {
				   	 txt += "<tr><td>" 
				   	 + tweetcount + "." + "</td>" +
				   	  "<td>" +  "<a id=\"" + myObj[x].userScreenName + "\"onclick=\"displayUser(\'" + myObj[x].userScreenName + "\')\"" +  ">" + "@" + myObj[x].userScreenName + "</a></td>" +
				   	  "<td>" + displayHashTags(myObj[x].tweetText) + "</td></tr>";
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


//function to clear the input field
function clearInput() {
document.getElementById('searchTerm').value = '';
}

/* function to process sentiment and 
   display according sentiment */
   
function processSentiment(sentiment) {
	if(sentiment == "neutral") {
	return " &#128528;";
	}
	else if(sentiment == "happy") {
	return " &#128522;";
	}
	else {
	return " &#128532;";
	}
}

/*function to display user profile */
function displayUser(userid) {
Spinner.show();
userTimeline = '';
jQuery.ajax({
            url: "http://localhost:9000/getUserTimeline/" + (userid),
            type: "GET",
            contentType: 'application/json; charset=utf-8',
            
            success: function(resultData) {
            result = JSON.parse(JSON.stringify(resultData));
            var UserDetails = result.data.tweets;
            userTimeline+=`<head>
            <title>User Profile</title>
			<style>
			table {
			text-align: center;
			}
			caption {
				padding-bottom: 20px;
				text-decoration: underline;
			}
			.center {
			text-align: center;
			}
			</style>
					<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
					<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
			</head>`
			var tweetcount = 1;
			userTimeline+=`<div class="container">
 				<div class="jumbotron">`
  			userTimeline+="<h2 class=\"center\">User Profile:"+ "@" +userid + "</h2></div></div>"
			 userTimeline+="<div class=\"row\"><div class=\"col-md-3\"></div><div class=\"col-md-6\">"
			 userTimeline += "<table border='1'>"
			 userTimeline +="<caption>*Latest 10 tweets of the above user</caption>"
 				 for (x in UserDetails) {
				   	 userTimeline += "<tr><td>" 
				   	 + tweetcount + "." + "</td>" +
				   	 "<td>" + UserDetails[x].tweetText + "</td></tr>";
				   	  tweetcount++;
				  }
     			 userTimeline += "</table>";
     		userTimeline+="<div class=\"col-md-3\"></div></div>"
             Spinner.hide();
			userWindow = window.open();
           	userWindow.document.write(userTimeline);
            },
            error : function(jqXHR, textStatus, errorThrown) {
                       Spinner.hide();
            },

            timeout: 120000,
        })

}

/*function to display word level statistics 
  for the search term selected */
function wordStats(keyterm) {
	Spinner.show();
	statscontent = '';
	jQuery.ajax({
            url: "http://localhost:9000/statistics/" + keyterm,
            type: "GET",
            contentType: 'application/json; charset=utf-8',
            success: function(resultData) {
            result = JSON.parse(JSON.stringify(resultData));
            var WordFrequency = result.data.wordfrequency;
            var sortable = [];
			for (var word in WordFrequency) {
    		sortable.push([word, WordFrequency[word]]);
			}
			sortable.sort(function(a, b) {
    			return a[1] - b[1];
			});
			sortable.reverse();
            statscontent+=`
            <html>
            <head>
            <title>SearchTerm Stats</title>
			<style>
			table, th, td {
			  border: 1px solid black;
			  border-collapse: collapse;
			  width: 60%;
			  text-align: center
			}
			
			table{
			  margin-left: auto; 
			  margin-right: auto;
			}
			caption {
				padding-bottom: 20px;
				text-decoration: underline;
			}
			</style>
			</head>`
            statscontent += "<body><table>"
      		statscontent += "<caption style='font-weight:600;font-color:black;'>" + "Word Level Statistics for search query tweet results :"+ keyterm  +
      			  "</caption>"
      		statscontent += "<tr><th>Word</th><th>Frequency</th></tr>"
            for(let wordcount in sortable)
            {
				statscontent+="<tr><td>" + sortable[wordcount][0] + "</td><td>" + sortable[wordcount][1] + "</td></tr>";
            }
           statscontent += "</table></body></html>"
           Spinner.hide();
			myWindow = window.open();
           	myWindow.document.write(statscontent);
            },
            error : function(jqXHR, textStatus, errorThrown) {
                       Spinner.hide();
            },

            timeout: 120000,
        })
            
}
