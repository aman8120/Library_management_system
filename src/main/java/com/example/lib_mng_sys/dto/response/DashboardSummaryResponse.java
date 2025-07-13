package com.example.lib_mng_sys.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DashboardSummaryResponse {

        private int users;
        private int books;
        private int authors;
        private int publishers;
        private long lendCount;
        private long returnCount;
        private List<MonthlyStat> monthlyStats;

        @Data
        @Builder
        public static class MonthlyStat {
            private String month;
            private long lend;
            private long returnCount;
        }
}
