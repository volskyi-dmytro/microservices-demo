package com.stpunk47.accounts.dto;

public record AccountsMsgDto(Long accountNumber,
                             String name,
                             String email,
                             String mobileNumber) {
}
