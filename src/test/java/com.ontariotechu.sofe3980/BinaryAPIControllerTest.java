package com.ontariotechu.sofe3980;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;


@RunWith(SpringRunner.class)
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("10001"));
    }
	@Test
    public void add2() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    @Test
    public void addZero() throws Exception {
        this.mvc.perform(get("/add")
                .param("operand1", "1010")
                .param("operand2", "0"))
            .andExpect(status().isOk())
            .andExpect(content().string("1010"));
    }

    @Test
    public void addCarryJSON() throws Exception {
        this.mvc.perform(get("/add_json")
                .param("operand1", "1111")
                .param("operand2", "1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.operand1").value("1111"))
            .andExpect(jsonPath("$.operand2").value("1"))
            .andExpect(jsonPath("$.result").value("10000"))
            .andExpect(jsonPath("$.operator").value("add"));
    }

    @Test
    public void addInvalidInput() throws Exception {
        this.mvc.perform(get("/add")
                .param("operand1", "abc")
                .param("operand2", "1"))
            .andExpect(status().isOk())
            .andExpect(content().string("1"));
    }

    @Test
    public void multiplySimple() throws Exception {
        this.mvc.perform(get("/add")
                .param("operand1", "10")
                .param("operator", "*")
                .param("operand2", "10"))
            .andExpect(status().isOk())
            .andExpect(content().string("100"));
    }

    @Test
    public void multiplyByZero() throws Exception {
        this.mvc.perform(get("/add")
                .param("operand1", "1011")
                .param("operator", "*")
                .param("operand2", "0"))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }

    @Test
    public void multiplyLarger() throws Exception {
        this.mvc.perform(get("/add")
                .param("operand1", "111")
                .param("operator", "*")
                .param("operand2", "11"))
            .andExpect(status().isOk())
            .andExpect(content().string("10101"));
    }

    @Test
    public void andBasic() throws Exception {
        this.mvc.perform(get("/add")
                .param("operand1", "1101")
                .param("operator", "&")
                .param("operand2", "1011"))
            .andExpect(status().isOk())
            .andExpect(content().string("1001"));
    }

    @Test
    public void andDifferentLength() throws Exception {
        this.mvc.perform(get("/add")
                .param("operand1", "101")
                .param("operator", "&")
                .param("operand2", "1"))
            .andExpect(status().isOk())
            .andExpect(content().string("1"));
    }

    @Test
    public void andWithZero() throws Exception {
        this.mvc.perform(get("/add")
                .param("operand1", "1111")
                .param("operator", "&")
                .param("operand2", "0"))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }

    @Test
    public void orBasic() throws Exception {
        this.mvc.perform(get("/add")
                .param("operand1", "1100")
                .param("operator", "|")
                .param("operand2", "1010"))
            .andExpect(status().isOk())
            .andExpect(content().string("1110"));
    }

    @Test
    public void orWithZero() throws Exception {
        this.mvc.perform(get("/add")
                .param("operand1", "1010")
                .param("operator", "|")
                .param("operand2", "0"))
            .andExpect(status().isOk())
            .andExpect(content().string("1010"));
    }

    @Test
    public void orZeroZero() throws Exception {
        this.mvc.perform(get("/add")
                .param("operand1", "0")
                .param("operator", "|")
                .param("operand2", "0"))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }

    @Test
    public void multiplyJSON() throws Exception {
        this.mvc.perform(get("/add_json")
                .param("operand1", "10")
                .param("operator", "*")
                .param("operand2", "10"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.operator").value("multiply"))
            .andExpect(jsonPath("$.result").value("100"));
    }
}