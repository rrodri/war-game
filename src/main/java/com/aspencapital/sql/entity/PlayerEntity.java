package com.aspencapital.sql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "Player")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerEntity
{
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer win_count;
}