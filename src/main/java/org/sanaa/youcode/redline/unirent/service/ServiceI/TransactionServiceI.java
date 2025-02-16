package org.sanaa.youcode.redline.unirent.service.ServiceI;

import org.sanaa.youcode.redline.unirent.model.dto.Request.TransactionRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.TransactionResponseDTO;

import java.util.List;

public interface TransactionServiceI {
    TransactionResponseDTO getTransactionById(Long id);
    List<TransactionResponseDTO> getAllTransactions();
    TransactionResponseDTO createTransaction(TransactionRequestDTO requestDTO);
    TransactionResponseDTO updateTransaction(Long id, TransactionRequestDTO requestDTO);
    void deleteTransaction(Long id);
}
