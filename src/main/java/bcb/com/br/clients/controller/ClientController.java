package bcb.com.br.clients.controller;

import bcb.com.br.clients.controller.dto.ClientRequest;
import bcb.com.br.clients.controller.dto.ClientResponse;
import bcb.com.br.clients.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<ClientResponse> create(@RequestBody @Valid ClientRequest request) {
        clientService.createClient(request);
        return null;
    }


}
