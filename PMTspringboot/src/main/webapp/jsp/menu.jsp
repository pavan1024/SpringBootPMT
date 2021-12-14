<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!Doctype html>
<html>
<style>
body {
	background: #00C9FF; /* fallback for old browsers */
	background: -webkit-linear-gradient(to right, #92FE9D, #00C9FF);
	/* Chrome 10-25, Safari 5.1-6 */
	background: linear-gradient(to right, #92FE9D, #00C9FF);
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}

.center {
	margin: auto;
	width: 600px;
	height: 100px;
	padding: 50px;
}

.btn-group button {
	background-color: #04AA6D; /* Green background */
	border: 1px solid green; /* Green border */
	color: white; /* White text */
	padding: 10px 24px; /* Some padding */
	cursor: pointer; /* Pointer/hand icon */
	width: 50%; /* Set a width if needed */
	display: block; /* Make the buttons appear below each other */
}

.btn-group button:not(:last-child) {
	border-bottom: none; /* Prevent double borders */
}

/* Add a background color on hover */
.btn-group button:hover {
	background-color: #3e8e41;
}
</style>
<body>
	<div class="center">
		<h1>Menu</h1>

		<div class="btn-group">
			<form name="CreateAccount" action="/account/createAccountForm">
				<button>Create Account</button>
			</form>
			<form name="DisplayAccountPassword"
				action="/account/displayPasswordForm">
				<button>Display Account Password</button>
			</form>
			<form name="DisplayAllAccountsbyGroupname"
				action="/account/displayByGroupForm">
				<button>Display All Accounts by Groupname</button>
			</form>
			<form name="Delete Account" action="/account/deleteAccountForm">
				<button>Delete Account</button>
			</form>
			<form name="UpdateAccountDetails" action="/account/submenu">
				<button>Update Account Details</button>

			</form>
			<form name="ModifyGroupname" action="/account/updateGroupnameForm">
				<button>Modify Groupname</button>
			</form>
			<form name="view All" action="/account/viewAll">
				<button>View All Accounts</button>

			</form>
			<form name="Delete Group" action="/account/deleteGroupForm">
				<button>Delete Group</button>
			</form>
			<br> <a href="/master/">Back</a>
		</div>
	</div>

</body>
</html>
