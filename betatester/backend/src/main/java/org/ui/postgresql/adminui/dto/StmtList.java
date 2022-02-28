package org.ui.postgresql.adminui.dto;

import org.ui.postgresql.adminui.dto.pk.StmtPK;

import javax.persistence.*;

@Entity(name = "smtp")
@Table(name = "stmt_list")
public class StmtList {
    @Id
    @EmbeddedId
    private StmtPK stmtPK;
    @Column
    private String query;

    public StmtPK getStmtPK() {
        return stmtPK;
    }

    public void setStmtPK(StmtPK stmtPK) {
        this.stmtPK = stmtPK;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
