<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatib1e" content="IE=Edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">

<title>Skritch</title>
<link rel="stylesheet" href="./ressources/css/index.css">
</head>

<body>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
		crossorigin="anonymous"></script>
		
	<c:forEach items="${cookie}" var="IdCookie">
		<c:if test="${IdCookie.key == 'username'}">
			<c:set var="cookieusername" value="${IdCookie.value.value}" scope="page" />
		</c:if>
	</c:forEach>

	<nav class="navbar">
		<div>
			<a href="connect"><h1>Home</h1></a>
			<p>Hulk smash</p>
		</div>
		<div id="log">
			<c:if test="${ !empty signInCheck }">
				<p style="color: red">
					<Strong><c:out value="${ signInCheck }" /></Strong>
				</p>
			</c:if>
			<c:if test="${ !empty sessionScope.user.username }">
				<form action="connect" method="post">
					<c:out value="Bonjour ${ sessionScope.user.username }" />
					<input name="submit" type="submit" value="disconnect">
				</form>
			</c:if>
			<c:if test="${ empty sessionScope.user.username }">
				<form id="login" action="connect" method="post">
					<c:if test="${ !empty sign || !empty log}">
						<input name="username" type="text" placeholder="username"
							value="${ cookieusername}">
						<input name="password" type="password" placeholder="password"
							value="${ password }">
					</c:if>
					<c:if test="${ !empty sign }">
						<input name="password2" type="password"
							placeholder="retype password">
					</c:if>
					<c:if test="${ empty sign }">
						<input name="submit" type="submit" value="log in">
						<c:if test="${ empty log && empty sign}">
							<input name="submit" type="submit" value="sign in">
						</c:if>
						<c:if test="${ !empty log }">
							<div id="remember">
								<label id="rememberlabel" for="checkbox">Remember me
								<input name="checkbox" type="checkbox" value="remember me" checked></label>
							</div>
						</c:if>
					</c:if>
					<c:if test="${ !empty sign }">
						<input name="submit" type="submit" value="save">
					</c:if>
				</form>
			</c:if>
		</div>
	</nav>

	<div class="skritch">
		<h1>
			Skritch skritch
		</hl>
			
			<c:if test="${ !empty sessionScope.user.videoGame }">
				<c:out value="Your favorite video game is ${ sessionScope.user.videoGame }" /></p>
			</c:if>
				
			<c:if test="${ !empty cookieusername }">
					<p><c:out value="Un cookie user est présent : ${ cookieusername }" /></p>
			</c:if>
					
			<br> <img id="skritch" src="./ressources/img/skritch.jpg"
				alt="alt skritch">
			<p>la balise p</p>
			<form action="connect" method="post">
				<button name="submit" value="click">Click me</button>
			</form>
			<a href="<c:url value="/connect">
                   <c:param name="mode" value="demo" />
                  </c:url>">test</a>
	</div>
	<c:if test="${ !empty sessionScope.username }">
		<div class="accordion" id="accordionExample">
			<div class="accordion-item">
				<h2 class="accordion-header" id="headingOne">
					<button class="accordion-button" type="button"
						data-bs-toggle="collapse" data-bs-target="#collapseOne"
						aria-expanded="true" aria-controls="collapseOne">
						Accordion Item #1</button>
				</h2>
				<div id="collapseOne" class="accordion-collapse collapse show"
					aria-labelledby="headingOne" data-bs-parent="#accordionExample">
					<div class="accordion-body">
						<img id="spook" id="image" src="./ressources/img/spook.jpg"
							alt="alt text">
					</div>
				</div>
			</div>
			<div class="accordion-item">
				<h2 class="accordion-header" id="headingTwo">
					<button class="accordion-button collapsed" type="button"
						data-bs-toggle="collapse" data-bs-target="#collapseTwo"
						aria-expanded="false" aria-controls="collapseTwo">
						Accordion Item #2</button>
				</h2>
				<div id="collapseTwo" class="accordion-collapse collapse"
					aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
					<div class="accordion-body">
						<img id="spook" id="image" src="./ressources/img/spook2.jpg"
							alt="alt text">
					</div>
				</div>
			</div>
		</div>
	</c:if>
</body>

</html>