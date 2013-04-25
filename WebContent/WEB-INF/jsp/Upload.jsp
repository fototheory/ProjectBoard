<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload Item</title>
</head>

<script language="JavaScript">
				function Validate()
				  {
					 var image =document.getElementById("image").value;
					 if(image!=''){
						  var checkimg = image.toLowerCase();
						  if (!checkimg.match(/(\.jpg|\.png|\.JPG|\.PNG|\.jpeg|\.JPEG)$/)){
							  alert("Please enter  Image  File Extensions .jpg,.png,.jpeg");
							  document.getElementById("image").focus();
							  return false;
						    }
						 }
					 return true;
				 }			
</script>
<body>
<form:form modelAttribute="upload" name="frm" method="post"
	enctype="multipart/form-data" onSubmit="return Validate();">
	
	<fieldset><legend>Upload File</legend>
	<table>
		<tr>
			<td><form:label for="description" path="description">File</form:label><br />
			</td>
			<td><form:input path="description" id="image" type="file" /></td>
		</tr>
		<tr>
			<td><br />
			</td>
			<td><input type="Submit" value="Upload" /></td>
		</tr>
	</table>
	</fieldset>

</form:form>

</body>
</html>