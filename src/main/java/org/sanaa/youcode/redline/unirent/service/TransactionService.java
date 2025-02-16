package org.sanaa.youcode.redline.unirent.service;

import jakarta.transaction.Transactional;
import org.sanaa.youcode.redline.unirent.model.dto.Request.TransactionRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.TransactionResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.Transaction;
import org.sanaa.youcode.redline.unirent.model.mapper.TransactionMapper;
import org.sanaa.youcode.redline.unirent.repository.TransactionRepository;
import org.sanaa.youcode.redline.unirent.service.ServiceI.TransactionServiceI;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TransactionService implements TransactionServiceI {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public TransactionResponseDTO getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Transaction not found"));
        return transactionMapper.toResponseDTO(transaction);
    }

    @Override
    public List<TransactionResponseDTO> getAllTransactions() {
        return transactionMapper.toResponseDTOList(transactionRepository.findAll());
    }

    @Override
    public TransactionResponseDTO createTransaction(TransactionRequestDTO requestDTO) {
        Transaction transaction = transactionMapper.toEntity(requestDTO);
        return transactionMapper.toResponseDTO(transactionRepository.save(transaction));
    }

    @Override
    public TransactionResponseDTO updateTransaction(Long id, TransactionRequestDTO requestDTO) {
        Transaction transaction = transactionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Transaction not found"));
        transactionMapper.updateEntityFromRequest(requestDTO, transaction);
        return transactionMapper.toResponseDTO(transactionRepository.save(transaction));
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
