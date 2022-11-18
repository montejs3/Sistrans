UPDATE Carrito 
SET IdUsuario = UsuarioCarro
FROM Carrito
WHERE getIdUsuario = NULL, rownum = 1;
