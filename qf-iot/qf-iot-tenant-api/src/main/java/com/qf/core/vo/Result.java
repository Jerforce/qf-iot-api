package com.qf.core.vo;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Result<T> {
    @NonNull
    private Integer code;
    @NonNull
    private String msg;
    private T data;
}
