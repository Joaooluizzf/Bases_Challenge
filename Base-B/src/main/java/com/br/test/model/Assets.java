package com.br.test.model;

import javax.persistence.*;

@Entity
@Table(name = "Debt")
public class Assets {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "value")
    private long value;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name="cpf")
    private Client client;

    public Assets(){
    }

    public Assets(Client client, long value, String description){
        this.client = client;
        this.value = value;
        this.description = description;
    }

    public long getId() {return id;}
    public long getValue(){return value;}
    public void setValue(long value){this.value=value;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public Client getClient() {return client;}
    public void setClient(Client client) {this.client = client;}
}
