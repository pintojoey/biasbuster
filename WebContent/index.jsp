<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bias Buster</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<body>
	<h2>Bias Buster<h2>
	<div style="display: none">
		<div id="template">
			<div class="card" style="width: 400px">
				<img class="card-img-top" src="img_avatar1.png" alt="Card image">
				<div class="card-body">
					<h4 class="card-title">Who am I?</h4>
					<h2 class="card-text"></h2>
					<button class="btn btn-primary" onClick="clicker('male')">GUY</button>
					<button class="btn btn-primary pull-right"  onClick="clicker('female')">GIRL</button>
				</div>
			</div>
		</div>
	</div>
	<div id="demo"></div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>
	<script>

			
			handle = prompt("Please enter your twitter handle");
			gender = prompt("What's your gender (M/F)?");
			loadDoc();
			var cards = [];
			var i = 0;
			var score=0;
			function showcard(i){
				var template=document.getElementById("template");
				template.getElementsByClassName("card-text")[0].innerHTML=cards[i].preference;
				document.getElementById("demo").innerHTML=template.innerHTML;

			}
			
			function clicker(gender){
				
				if(cards[i].answer==gender){
					score++;
				}
				else{
					alert("Oops you guessed it wrong! "+cards[i].handle+" is a "+cards[i].answer);
					alert("You can lookup "+cards[i].handle+" on Twitter")
				}
				i++;
				if(i<cards.length)
					showcard(i);
				else {
					alert("Your score is "+score+"/10")
					alert("Thats it for today!");
				}
		
			}
			function loadDoc() {

				if (gender == "M" || gender == "m")
					gender = "male";
				else
					gender = "female";

				var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 200) {

						cards = JSON.parse(this.responseText);
						showcard(0);
						console.log(cards)
					}
				};
				xhttp.open("GET", "rest/services/cards/" + handle + "/" + gender, true);
				xhttp.send();
			}
			
			
			
			
		</script>
</body>

</html>