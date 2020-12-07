let txt = "";
let finaltext = "";
let count = 1;
Spinner();
Spinner.show();
var input = document.getElementById("searchTerm");
input.addEventListener("keyup", function(event) {
	if (event.keyCode === 13 && count < 11) {
		search();
	}
});

/*function to show tweet words by search term */
function searchDisplay(resultData) {
	if (count < 11) {
		count++;
	}
	else {
		finaltext = "";
		txt = "";
		count = 1;
	}
	if (count === 11) {
		document.getElementById('goButton').disabled = true;
	}
	var result = (JSON.parse(resultData));
	var tweets = result.data.tweets;
	myObj = JSON.parse(JSON.stringify(tweets));
	if (result.data.isNewData === false) {
		txt += "<table id=\"" + result.data.keyword + "\" border='1'>"
		txt += "<caption>" + "Search terms:" + "<a id=\"" + result.data.keyword + "\"onclick=\"wordStats(\'" + result.data.keyword + "\')\"" + ">  " + result.data.keyword + "</a>  " +
			processSentiment(result.data.sentiment) +
			"</caption>"
		for (x in myObj) {
			txt += "<tr><td>" + "*" + "</td>" +
				"<td>" + "<a id=\"" + myObj[x].userScreenName + "\"onclick=\"displayUser(\'" + myObj[x].userScreenName + "\')\"" + ">" + "@" + myObj[x].userScreenName + "</a></td>" +
				"<td>" + displayHashTags(myObj[x].tweetText) + "</td></tr>";
		}
		txt += "</table>"
		finaltext = txt + finaltext;
		txt = "";
		document.getElementById("demo").innerHTML = finaltext;
		Spinner.hide();

	} else {
		for (x in myObj) {
			txt += "<td>" + "*" + "</td>" +
				"<td>" + "<a id=\"" + myObj[x].userScreenName + "\"onclick=\"displayUser(\'" + myObj[x].userScreenName + "\')\"" + ">" + "@" + myObj[x].userScreenName + "</a></td>" +
				"<td>" + displayHashTags(myObj[x].tweetText) + "</td>";
			var table = document.getElementById(result.data.keyword);
			var row = table.insertRow(0);
			row.innerHTML = txt
			txt = ""
		}
		count--;
		Spinner.hide();
	}
}
/*,
error : function(jqXHR, textStatus, errorThrown) {
Spinner.hide();
},

timeout: 120000,
})
}*/

//function to display hashtags
function displayHashTags(input) {
	input = input.replace(/(^|\s)(#[a-z\d-]+)/ig, "$1<a onclick=\"processHashTags('$2')\">$2</a>");
	return input;
}

//function to display hashtags
function DisplayHashTagResult(resultData) {
	if (count < 11) {
		count++;
	}
	else {
		finaltext = "";
		txt = "";
		count = 1;
	}
	if (count === 11) {
		document.getElementById('goButton').disabled = true;
	}
	var result = JSON.parse(resultData);
	var tweets = result.data.tweets;
	myObj = JSON.parse(JSON.stringify(tweets));
	if (result.data.isNewData === false) {
		txt += "<table id=\"" + result.data.hashtag + "\" border='1'>"
		txt += "<caption>" + "Hashtag Result:" + result.data.hashtag
		"</caption>"
		for (x in myObj) {
			txt += "<tr><td>" + "*" + "</td>" +
				"<td>" + "<a id=\"" + myObj[x].userScreenName + "\"onclick=\"displayUser(\'" + myObj[x].userScreenName + "\')\"" + ">" + "@" + myObj[x].userScreenName + "</a></td>" +
				"<td>" + displayHashTags(myObj[x].tweetText) + "</td></tr>";
		}
		txt += "</table>"
		finaltext = txt + finaltext;
		txt = "";
		document.getElementById("demo").innerHTML = finaltext;
		Spinner.hide();

	} else {
		txt = "------------------------"
		for (x in myObj) {
			txt += "<td>"
				+ "*" + "</td>" +
				"<td>" + "<a id=\"" + myObj[x].userScreenName + "\"onclick=\"displayUser(\'" + myObj[x].userScreenName + "\')\"" + ">" + "@" + myObj[x].userScreenName + "</a></td>" +
				"<td>" + displayHashTags(myObj[x].tweetText) + "</td>";
			var table = document.getElementById(result.data.hashtag);
			var row = table.insertRow(0);
			row.innerHTML = txt
			txt = "";
		}
		count--;
		Spinner.hide();
	}
}


//function to clear the input field
function clearInput() {
	document.getElementById('searchTerm').value = '';
}

/* function to process sentiment and 
   display according sentiment */

function processSentiment(sentiment) {
	if (sentiment == "neutral") {
		return " &#128528;";
	}
	else if (sentiment == "happy") {
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
			userTimeline += `<head>
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
			userTimeline += `<div class="container">
 				<div class="jumbotron">`
			userTimeline += "<h2 class=\"center\">User Profile:" + "@" + userid + "</h2></div></div>"
			userTimeline += "<div class=\"row\"><div class=\"col-md-3\"></div><div class=\"col-md-6\">"
			userTimeline += "<table border='1'>"
			userTimeline += "<caption>*Latest 10 tweets of the above user</caption>"
			for (x in UserDetails) {
				userTimeline += "<tr><td>"
					+ tweetcount + "." + "</td>" +
					"<td>" + UserDetails[x].tweetText + "</td></tr>";
				tweetcount++;
			}
			userTimeline += "</table>";
			userTimeline += "<div class=\"col-md-3\"></div></div>"
			Spinner.hide();
			window[keyterm] = window.open();
			window[keyterm].document.write(userTimeline);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			Spinner.hide();
		},

		timeout: 120000,
	})

}

/*function to display word level statistics 
  for the search term selected */
function displayWordStats(resultData,keyterm) {
			result = JSON.parse(resultData);
			var WordFrequency = result.data.wordfrequency;
			var sortable = [];
			for (var word in WordFrequency) {
				sortable.push([word, WordFrequency[word]]);
			}
			sortable.sort(function(a, b) {
				return a[1] - b[1];
			});
			sortable.reverse();
			var statscontent = '';
			statscontent += `
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
			</style>` 
			statscontent +=	"<script>  const keyterm = \""+ keyterm + "\"; window.addEventListener(\"message\", (event) => { var element = document.getElementById(keyterm); element.innerHTML = event.data;} , false);</script></head>"
			statscontent += "<body id=\""+ keyterm +"\"><table>";
			statscontent += "<caption style='font-weight:600;font-color:black;'>" + "Word Level Statistics for search query tweet results :" + keyterm +
				"</caption>"
			statscontent += "<tr><th>Word</th><th>Frequency</th></tr>"
			for (let wordcount in sortable) {
				statscontent += "<tr><td>" + sortable[wordcount][0] + "</td><td>" + sortable[wordcount][1] + "</td></tr>";
			}
			statscontent += "</table></body></html>"
			Spinner.hide();
			window[keyterm] = window.open("",keyterm);
			window[keyterm].document.write(statscontent);
}

let searchSocket = new WebSocket("ws://localhost:9000/getTweetsBySearchViaWebSocket");
searchSocket.onopen = function(e) {
	Spinner.hide();
};
//Web Socket implemnetation for searching keyword
function search() {
	var searchkey = document.getElementById("searchTerm").value;
	Spinner.show();

	let message = {
		"keyword": searchkey
	};
	let msg = JSON.stringify(message);
	searchSocket.send(msg);
	searchSocket.onmessage = function(event) {
		var response = event.data;
		searchDisplay(response);
		Spinner.hide();
	}

	searchSocket.onclose = function(event) {
		if (event.wasClean) {
			alert(`[close] Connection closed cleanly, code=${event.code} reason=${event.reason}`);
		} else {
			alert('[close] Connection died');
		}
	};

	searchSocket.onerror = function(error) {
		Spinner.hide();
		alert(`[error] ${error.message}`);
	};
}

let hashTagSocket = new WebSocket("ws://localhost:9000/getTweetsByHashtagViaWebSocket");
hashTagSocket.onopen = function(e) {
};

//Web Socket implemnetation for displaying hashtags
function processHashTags(hashtag) {
	Spinner.show();

	let message = {
		"hashtag": hashtag
	};
	let msg = JSON.stringify(message);
	hashTagSocket.send(msg);
	hashTagSocket.onmessage = function(event) {
		var response = event.data;
		DisplayHashTagResult(response);
		Spinner.hide();
	}

	hashTagSocket.onclose = function(event) {
		if (event.wasClean) {
			alert(`[close] Connection closed cleanly, code=${event.code} reason=${event.reason}`);
		} else {
			alert('[close] Connection died');
		}
	};

	hashTagSocket.onerror = function(error) {
		Spinner.hide();
		alert(`[error] ${error.message}`);
	};
}

//Web Socket implemnetation for displaying statistics
let wordStatsSocket = new WebSocket("ws://localhost:9000/getTweetStatisticsViaWebSocket");
wordStatsSocket.onopen = function(e) {
};
function wordStats(keyterm) {
	Spinner.show();
	let message = {
		"keyword": keyterm
	};
	let msg = JSON.stringify(message);
	wordStatsSocket.send(msg);
	wordStatsSocket.onmessage = function(event) {
		var response = event.data;
		var parsedRepsonse = JSON.parse(event.data);
		if(parsedRepsonse.data.isNewData === false) 
		{
			displayWordStats(response,keyterm);
		}
		else {
			var WordFrequency = parsedRepsonse.data.wordfrequency;
			var sortable = [];
			for (var word in WordFrequency) {
				sortable.push([word, WordFrequency[word]]);
			}
			sortable.sort(function(a, b) {
				return a[1] - b[1];
			});
			sortable.reverse();
			var statscontent = '';
			statscontent += "<body id=\""+ keyterm +"\"><table>";
			statscontent += "<caption style='font-weight:600;font-color:black;'>" + "Word Level Statistics for search query tweet results :" + keyterm +
				"</caption>"
			statscontent += "<tr><th>Word</th><th>Frequency</th></tr>"
			for (let wordcount in sortable) {
				statscontent += "<tr><td>" + sortable[wordcount][0] + "</td><td>" + sortable[wordcount][1] + "</td></tr>";
			}
			statscontent += "</table></body>"
			window[keyterm].postMessage(statscontent);
		}

		Spinner.hide();
	}

	wordStatsSocket.onclose = function(event) {
		if (event.wasClean) {
			alert(`[close] Connection closed cleanly, code=${event.code} reason=${event.reason}`);
		} else {
			alert('[close] Connection died');
		}
	};

	wordStatsSocket.onerror = function(error) {
		Spinner.hide();
		alert(`[error] ${error.message}`);
	};
}
