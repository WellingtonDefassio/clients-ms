package bcb.com.br.clients.controller;

import bcb.com.br.clients.controller.dto.*;
import bcb.com.br.clients.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<CreateClientResponse> create(@RequestBody @Valid CreateClientRequest request) {
        return new ResponseEntity<>(clientService.createClient(request), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<GetClientResponse> get(@RequestParam String cnpj) {
        return new ResponseEntity<>(clientService.getClient(cnpj), HttpStatus.OK);
    }

    @PostMapping("/value")
    public ResponseEntity<GetClientResponse> addValue(@RequestBody ReceiveEntries receiveEntries) {
      return ResponseEntity.ok(clientService.addValue(receiveEntries));
    }

    @PostMapping("/charge")
    public ResponseEntity<GetClientResponse> chargeValue(@RequestBody ReceiveEntries receiveEntries) {
        return ResponseEntity.ok(clientService.chargeMessage(receiveEntries));
    }

    @PutMapping("/limit")
    public ResponseEntity<GetClientResponse> changeLimit(@RequestBody ChangeLimitRequest request) {
        return ResponseEntity.ok(clientService.changeLimit(request));
    }

    @PutMapping("/type")
    public ResponseEntity<GetClientResponse> changeType(@RequestBody ChangeTypeRequest request) {
        return ResponseEntity.ok(clientService.changeType(request));
    }
}
