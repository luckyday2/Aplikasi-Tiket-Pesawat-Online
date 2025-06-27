    package com.example.user_service.Model;

    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;

    @Entity
    public class UserModel {
        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        private Long id;
        private String nama;
        private String alamat;
        private String noHp;

        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getNama() {
            return nama;
        }
        public void setNama(String nama) {
            this.nama = nama;
        }
        public String getAlamat() {
            return alamat;
        }
        public void setAlamat(String alamat) {
            this.alamat = alamat;
        }
        public String getNoHp() {
            return noHp;
        }
        public void setNoHp(String noHp) {
            this.noHp = noHp;
    }
}
