<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="utf-8">
      <title>Delete Product</title>
      <link rel="stylesheet" href="../css/dashboard.css" />
      <link rel="stylesheet" href="../css/bootstrap-theme.min.css" />
      <link rel="stylesheet" href="../css/bootstrap.min.css" />
      <script src="../js/third-party/jquery-2.2.4.min.js"></script>
      <link rel="stylesheet" href="../css/inventory.css"/>
   </head>
   <body>
      <div  class="inventory-container edit-container">
         <form action="/deleteOrder" method="post" id="productForm">
            <div class="form-group">
               <label>Do you really want to delete this product?</label>
            </div>
            <input type="SUBMIT" class="btn btn-default" value="Submit" />
            <input type="button" id="resetBtn" class="btn btn-default" value="Cancel"/>
         </form>
      </div>
   </body>
</html>