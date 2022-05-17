package com.operator.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.operator.exception.ResourceNotFoundException;
import com.operator.model.Channel;
import com.operator.repository.ChannelRepository;

@RestController
@RequestMapping("/api/v1")
public class ChannelController {

	@Autowired
	private ChannelRepository channelRepository;

	@GetMapping("/channels/read")
	public List<Channel> getAllChannels() {
		return channelRepository.findAll();
	}

	@PostMapping("/channels/create")
	public Channel createChannel(@Valid @RequestBody Channel channel) {
		return channelRepository.save(channel);
	}

	@GetMapping("/channels/read/{category}")
	public List<Channel> getAllChannelsByCategory(@PathVariable(value = "category") String category) {
		List<Channel> channelList = new ArrayList<Channel>();
		for (Channel channel : channelRepository.findAll()) {
			if (channel.getCategory().toString().equals(category)) { 
				channelList.add(channel);
			}
		}
		return channelList;
	}

	@GetMapping("/channels/read/{id}")
	public Channel getChannelById(@PathVariable(value = "id") Long channelId) {
		return channelRepository.findById(channelId)
				.orElseThrow(() -> new ResourceNotFoundException("Channel", "id", channelId));
	}

	@PutMapping("/channels/update/{id}")
	public Channel updateChannel(@PathVariable(value = "id") Long channelId,
			@Valid @RequestBody Channel channelDetails) {

		Channel channel = channelRepository.findById(channelId)
				.orElseThrow(() -> new ResourceNotFoundException("Channel", "id", channelId));

		channel.setName(channelDetails.getName());
		channel.setPrice(channelDetails.getPrice());
		channel.setSuppliers(channelDetails.getSuppliers());

		Channel updatedChannel = channelRepository.save(channel);
		return updatedChannel;
	}

	@DeleteMapping("/channels/delete/{id}")
	public ResponseEntity<?> deleteChannel(@PathVariable(value = "id") Long channelId) {
		Channel channel = channelRepository.findById(channelId)
				.orElseThrow(() -> new ResourceNotFoundException("Channel", "id", channelId));

		channelRepository.delete(channel);

		return ResponseEntity.ok().build();
	}

}
