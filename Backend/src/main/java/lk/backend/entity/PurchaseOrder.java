package lk.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrder {

    @Id
    private String id;
    private String orderReference;
    private String deliverNote;
    @ManyToOne
    private AppUser warehouseManager;
    @ManyToOne
    private AppUser supplier;
    @Transient
    private int poQuantity;
    @Transient
    private int soQuantity;
    @Transient
    private double poTotal;
    @Transient
    private double soTotal;
    private String poStatus;
    private String soStatus;
    private boolean poFinalized;
    private boolean soFinalized;
    private boolean poAccepted;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchaseOrder")
    private Set<PurchaseOrderDetail> purchaseOrderDetails;
    @Transient
    private List<PurchaseOrderDetail> purchaseOrderDetailList;

    public PurchaseOrder(PurchaseOrder purchaseOrder) {
        this.id = purchaseOrder.id;
        this.poStatus = purchaseOrder.poStatus;
        this.soStatus = purchaseOrder.soStatus;
        this.poQuantity = purchaseOrder.poQuantity;
        this.soQuantity = purchaseOrder.soQuantity;
        this.poFinalized = purchaseOrder.poFinalized;
        this.soFinalized = purchaseOrder.soFinalized;
        this.orderReference = purchaseOrder.orderReference;
        this.deliverNote = purchaseOrder.deliverNote;
        this.poAccepted = purchaseOrder.poAccepted;
    }

    public PurchaseOrder(PurchaseOrder purchaseOrder, AppUser warehouseManager, AppUser supplier) {
        this(purchaseOrder);
        if (supplier != null) {
            this.supplier = new AppUser(supplier);
        }
        if (warehouseManager != null) {
            this.warehouseManager = new AppUser(warehouseManager);
        }
    }
}
