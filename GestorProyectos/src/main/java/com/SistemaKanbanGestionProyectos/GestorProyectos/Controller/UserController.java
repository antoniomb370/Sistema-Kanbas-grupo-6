package com.SistemaKanbanGestionProyectos.GestorProyectos.Controller;

import com.SistemaKanbanGestionProyectos.GestorProyectos.model.AuthenticationReq;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.TokenInfo;
import com.SistemaKanbanGestionProyectos.GestorProyectos.security.JwtUtilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

@RestController
@RequestMapping("/v1/login")
@Tag(name = "Usuario controller", description = "Metodo para la autenticacion del usuario")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;
      @Autowired
    UserDetailsService usuarioDetailsService;
    @Autowired
    private JwtUtilService jwtUtilService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Operation(summary = "Login",
            description = "Creacion del login para la generacion del token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Su respuesta ha sido exitosa."),
            @ApiResponse(responseCode = "400", description = "Bad Request, Algo ingresaste mal. Verifica la información."),
            @ApiResponse(responseCode = "301", description = "Credenciales erroneas o permisos no otorgados."),
            @ApiResponse(responseCode = "403", description = "Credenciales insuficientes para visualizar la lista de los projectos."),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema, comuniquese con el proveedor")
    })
    @PostMapping("/authenticate1")
    public ResponseEntity<TokenInfo> authenticate(@RequestBody AuthenticationReq authenticationReq) {
        logger.info("Autenticando al usuario {}", authenticationReq.getUsuario());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationReq.getUsuario(),
                        authenticationReq.getClave()));

        final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(
                authenticationReq.getUsuario());

        final String jwt = jwtUtilService.generateToken(userDetails);

        return ResponseEntity.ok(new TokenInfo(jwt));
    }

}